<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 文章管理</el-breadcrumb-item>
        <el-breadcrumb-item>文章列表</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-row class="toolbar">
      <el-col :span="5" :offset="16">
        <el-input v-model="searchVal" placeholder="请输入标题" @keyup.enter.native="search"></el-input>
      </el-col>
      <el-col :span="1" class="search-box">
        <el-button type="primary" icon="search" @click="handleSearch" :loading="loading">查询</el-button>
      </el-col>
    </el-row>
    <el-table :data="tableData" style="width: 100%" v-loading.body="loading"
              :default-sort="{prop: 'createdAt', order: 'descending'}">
      <el-table-column prop="title" label="文章标题" >
      </el-table-column>
      <el-table-column prop="createdAt" label="发布日期" sortable >
        <template scope="scope">{{scope.row.createdAt | formatDateFilter}}</template>
      </el-table-column>
      <el-table-column prop="commentsNum" label="评论数">
      </el-table-column>
      <el-table-column label="分类">
        <template scope="scope">
          <el-tag type="primary">{{scope.row.category.name}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="tags" label="标签" width="250"
        :filters="filters.tags"
        :filter-method="filterTag" header-align="center" align="center">
        <template scope="scope" inline-template>
          <el-tag v-for="tag in scope.row.tags" :key="tag.id" close-transition>{{tag.name}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态" width="250"
                       :filters="filters.status"
                       :filter-method="filterStatus"
                       header-align="center" align="center">
        <template scope="scope" inline-template>
          <el-tag v-if="scope.row.status==1" :type="'gray'" close-transition>草稿</el-tag>
          <el-tag v-if="scope.row.status==2" :type="'success'" close-transition>已发布</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200" fixed="right">
        <template scope="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button v-if="scope.row.status==1" size="small" type="primary"  @click="handlePush(scope.$index, scope.row)">发布</el-button>
          <el-button v-if="scope.row.status==2" size="small" type="primary"  @click="handleRevoke(scope.$index, scope.row)">撤回</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination">
      <el-pagination @current-change="handleCurrentChange" layout="total, prev, pager, next"
                     :total="totalElements"
                     :page-size="pageSize"
                     :current-page="currentPage">
      </el-pagination>
    </div>
  </div>
</template>
<script>
  import api from '../../../provider/api'
  export default {
    data() {
      return {
        loading: false,
        tableData: [],
        currentPage: 1,
        pageSize: 10,
        totalElements: 0,
        searchVal: '',
        tags:[],
        filters:{
            tags:[],
            status:[{text:'草稿',value:1},{text:'已发布',value:2}]
        }
      }
    },
    mounted () {
      this.getArticles(this.currentPage, this.pageSize)
      api.getTags().then((data)=>{
        let tags = data.data.data
        this.filters.tags = tags.map((o)=>{
            return {value:o.id, text:o.name}
        })
      })
    },
    methods: {
      async getArticles(pageNum, pageSize, keyword) {
        this.loading = true
        const result = await api.getArticlesByPage(pageNum, pageSize, keyword)
        this.tableData = result.data.data.content
        this.totalElements = result.data.data.totalElements
        this.currentPage = result.data.data.number + 1
        this.loading = false
      },
      handleEdit(index, row) {
        this.$router.push({ name: 'editArticle', params: { aid: row.id }})
      },
      async handlePush(index, row) {
        this.loading = true
        await api.modifyArticleStatus(row.id, 2)
        this.reloadTable()
        this.loading = false
      },
      async handleRevoke(index, row){
        this.loading = true
        await api.modifyArticleStatus(row.id, 1)
        this.reloadTable()
        this.loading = false
      },
      handleDelete(index, row) {
        this.$confirm('删除后不可恢复，确定删除？ ', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.loading = true
          api.removeArticle(row.id).then((data)=>{
            this.$message.success('删除文章成功')
            this.loading = false
            this.getArticles(1, this.pageSize, this.searchVal)
          }).catch((msg) => {
            this.$message.error(msg)
            this.loading = false
          })
        }).catch(() => {})
      },
      handleCurrentChange(val) {
        this.currentPage = val
        this.getArticles(val, this.pageSize)
      },
      handleSearch(){
        if (!this.loading) {
          this.reloadTable()
        }
      },
      filterTag(value, row) {
        let rowTag = row.tags.map((o)=>{
          return o.id
        })
        return rowTag.indexOf(value) !== -1
      },filterStatus(value, row){
        return row.status===value
      },
      reloadTable(){
        this.getArticles(1, this.pageSize, this.searchVal)
      }
    }

  }
</script>
<style scoped>
  .el-tag {
    margin-right: 10px;
  }

  .toolbar {
    margin: 15px 0;
  }

  .toolbar .search-box {
    margin-left: 10px;
  }
</style>
