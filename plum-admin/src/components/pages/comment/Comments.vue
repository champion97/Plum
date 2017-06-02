<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 评论管理</el-breadcrumb-item>
        <el-breadcrumb-item>评论列表</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-row class="toolbar">
      <el-col :span="2" :offset="16">
        <el-select v-model="audited" clearable placeholder="状态">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="1" class="search-box">
        <el-button type="primary" icon="search" @click="handleSearch" :loading="loading">查询</el-button>
      </el-col>
    </el-row>
    <el-table :data="tableData" style="width: 100%" v-loading.body="loading">
      <el-table-column prop="id" label="ID" >
      </el-table-column>
      <el-table-column prop="author" label="评论者" >
      </el-table-column>
      <el-table-column prop="mail" label="邮箱" >
      </el-table-column>
      <el-table-column prop="text" label="评论内容" >
      </el-table-column>
      <el-table-column prop="createdAt" label="评论日期" sortable >
        <template scope="scope">{{scope.row.createdAt | formatDateTimeFilter}}</template>
      </el-table-column>
      <el-table-column prop="audited" label="状态" width="250">
        <template scope="scope" inline-template>
          <el-tag v-if="scope.row.audited" :type="'success'" close-transition>审核通过</el-tag>
          <el-tag v-else :type="'gray'" close-transition>待审核</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200" fixed="right">
        <template scope="scope">
          <el-button size="small" v-if="!scope.row.audited"  type="primary"  @click="handleAudit(scope.$index, scope.row)">通过审核</el-button>
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
        options: [{
          value: 'true',
          label: '审核通过'
        }, {
          value: 'false',
          label: '待审核'
        }],
        audited:''
      }
    },
    mounted () {
      this.getComments(this.currentPage, this.pageSize)
    },
    methods: {
      async getComments(pageNum, pageSize, audited) {
        this.loading = true
        const result = await api.getCommentsByPage(pageNum, pageSize, audited)
        this.tableData = result.data.data.content
        this.totalElements = result.data.data.totalElements
        this.currentPage = result.data.data.number + 1
        this.loading = false
      },
      async handleAudit(index, row){
        this.loading = true
        await api.auditComment(row.id)
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
          api.removeComment(row.id).then((data)=>{
            this.$message.success('删除评论成功')
            this.loading = false
            this.getComments(1, this.pageSize, this.audited)
          }).catch((msg) => {
            this.$message.error(msg)
            this.loading = false
          })
        }).catch(() => {})
      },
      handleCurrentChange(val) {
        this.currentPage = val
        this.getComments(val, this.pageSize)
      },
      handleSearch(){
        if (!this.loading) {
          this.reloadTable()
        }
      },
      reloadTable(){
        this.getComments(1, this.pageSize, this.audited)
      }
    }

  }
</script>
<style scoped>


  .toolbar {
    margin: 15px 0;
  }

  .toolbar .search-box {
    margin-left: 10px;
  }
</style>
