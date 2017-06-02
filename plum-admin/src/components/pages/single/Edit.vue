<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 单页面管理</el-breadcrumb-item>
        <el-breadcrumb-item>创建单页面</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <div class="editor-wrapper">
        <el-form-item class="editor-wrapper" prop="title">
          <input v-model="form.title" class="title" type="text" placeholder="请输入标题"/>
        </el-form-item>
        <el-form-item class="editor-wrapper" prop="content">
          <textarea id="editor" placeholder="Content here ...."></textarea>
        </el-form-item>
      </div>
      <el-row>
        <el-col :span="4">
          <el-form-item label="单页面" prop="slug">
            <el-input placeholder="slug" v-model="form.slug">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form', 2)"
                   v-loading.fullscreen.lock="fullscreenLoading"
                   element-loading-text="单页面保存...">保存
        </el-button>
        <el-button @click="goBack()">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import api from '../../../provider/api'
  export default {
    data() {
      return {
        editor: '',
        form: {
          title: '',
          content: '',
          slug:''
        },
        fullscreenLoading: false,
        dialogVisible: false,
        rules: {
          title: [
            {required: true, message: '请输入标题', trigger: 'blur'},
            {min: 3, max: 100, message: '长度在 3 到 100 个字符', trigger: 'blur'}
          ],
          slug: {required: true, message: '请输入slug', trigger: 'blur'}
        }
      }
    },
    mounted(){
      this.editor = new Editor()
      this.editor.render()
    },
    methods: {
      submitForm(formName, status) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.form.content = this.editor.codemirror.getValue()
            this.fullscreenLoading = true
            this.form.status = status
            let self = this
            api.addSinglePage(this.form).then((data) => {
              self.$notify({
                title: '提示',
                type: 'success',
                message: '保存成功'
              });
              self.fullscreenLoading = false
            }).catch((msg) => {
              self.$notify.error({
                title: '错误',
                message: msg
              });
              self.fullscreenLoading = false
            })
          } else {
            return false;
          }
        })
      },
      goBack(){
        this.$confirm('确定返回？ ', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$router.go(-1)
        })
      }
    }

  }
</script>
<style scoped>
  form {
    margin-top: 50px
  }
</style>
