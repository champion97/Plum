<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 标签管理</el-breadcrumb-item>
        <el-breadcrumb-item>标签列表</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-row class="toolbar">
      <el-col :span="1">
        <el-button type="primary" icon="plus" @click="handleAdd()">新增</el-button>
      </el-col>
    </el-row>
    <el-table :data="tableData" style="width: 100%" v-loading.body="loading"
              :default-sort="{prop: 'id', order: 'descending'}">
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="name" label="分类名称"></el-table-column>
      <el-table-column label="操作" width="180">
        <template scope="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
  import api from '../../../provider/api'
  export default {
    data() {
      return {
        loading: false,
        tableData: []
      }
    },
    mounted () {
        this.getTags()
    },
    methods: {
        async getTags(){
            this.loading=true
            let data = await api.getTags()
            this.tableData = data.data.data
            this.loading=false
        },
      handleAdd() {
        this.$prompt('请输入分类名称', {
          confirmButtonText: '确定',
          inputValidator(value){
              return !!value
          },
          inputErrorMessage: '不能为空'
        }).then(({ value }) => {
          this.loading=true
          api.addTag({name:value}).then((data)=>{
            this.$message.success('添加成功');
            this.loading=false
            this.getTags()
            }).catch((msg)=>{
              this.$message.error(msg);
            this.loading=false
            })
        }).catch(() => {})
      },
      handleEdit(index, row) {
        this.$prompt('请输入分类名称', {
          confirmButtonText: '确定',
          inputValue: row.name,
          inputValidator(value){
            return !!value
          },
          inputErrorMessage: '不能为空'
        }).then(({ value }) => {
          this.loading=true
          api.modifyTag({id:row.id,name:value}).then((data)=>{
            this.$message.success('保存成功');
            this.loading=false
            this.getTags()
          }).catch((msg)=>{
            this.$message.error(msg);
            this.loading=false
          })
        }).catch(() => {})
      },
      handleDelete(index, row) {
        this.$confirm('删除后不可恢复，确定删除？ ', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.loading = true
          api.removeTag(row.id).then((data)=>{
            this.$message.success('删除分类成功');
            this.loading = false
            this.getTags()
          }).catch((msg) => {
            this.$message.error(msg);
            this.loading = false
          });
        }).catch(() => {})
      }
    }

  }
</script>
<style scoped>

  .toolbar {
    margin: 15px 0;
  }

</style>
