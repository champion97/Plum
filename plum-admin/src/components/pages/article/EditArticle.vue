<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 文章管理</el-breadcrumb-item>
        <el-breadcrumb-item>编辑文章</el-breadcrumb-item>
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
        <el-col :span="8">
          <el-form-item label="封面" prop="cover">
            <el-input placeholder="请输入图片URL" v-model="form.cover">
              <el-button slot="append" @click="dialogVisible=true">预览</el-button>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="分类" prop="category">
        <el-select v-model="form.category" placeholder="请选择分类">
          <el-option v-for="item in categories" :label="item.name" :value="item.id" :key="item.id"></el-option>
        </el-select>
      </el-form-item>
     <!-- <el-form-item label="标签" prop="tags">
        <el-checkbox-group v-model="form.tags">
          <el-checkbox v-for="item in tags" :label="item.id" :key="item.id">{{item.name}}</el-checkbox>
        </el-checkbox-group>
      </el-form-item>-->
      <el-form-item label="标签" prop="tags">
        <el-select
          v-model="form.tags"
          multiple
          filterable
          placeholder="标签">
          <el-option
            v-for="item in tags"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="允许评论" prop="allowComment">
        <el-switch on-text="" off-text="" v-model="form.allowComment"></el-switch>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form', 1, $event)"
                   v-loading.fullscreen.lock="fullscreenLoading"
                   element-loading-text="龟速保存中 =w=">
          <template v-if="form.status===1">保存</template>
          <template v-if="form.status===2">撤回&保存</template>
        </el-button>
        <el-button type="primary" @click="submitForm('form', 2, $event)"
                   v-loading.fullscreen.lock="fullscreenLoading"
                   element-loading-text="龟速发布中 =w=">
          <template v-if="form.status===1">发布</template>
          <template v-if="form.status===2">保存</template>
        </el-button>
        <el-button @click="goBack()">取消</el-button>
      </el-form-item>
    </el-form>
    <el-dialog title="封面图" v-model="dialogVisible" size="tiny">
      <img :src="form.cover" width="100%">
    </el-dialog>
  </div>
</template>
<script>
  import api from '../../../provider/api'
  export default {
    data() {
      return {
        editor: '',
        categories: [],
        tags: [],
        form: {
          id:'',
          title: '',
          category: '',
          content:'',
          tags: [],
          allowComment: true,
          status: 1,
          cover: ''
        },
        fullscreenLoading: false,
        dialogVisible: false,
        rules: {
          title: [
            {required: true, message: '请输入标题', trigger: 'blur'},
            {min: 3, max: 100, message: '长度在 3 到 100 个字符', trigger: 'blur'}
          ],
          cover: [{type: 'url', required: false, message: '格式不正确', trigger: 'change'}],
          category: [{type: 'number',required: true, message: '请选择分类', trigger: 'change'}],
          tags: [{type: 'array', required: true, message: '请选择至少一个标签', trigger: 'change'}]
        }
      }
    },
    created(){
      (async() =>{
        let tagsData = await api.getTags()
        this.tags = tagsData.data.data
        let categoriesData = await api.getCategories()
        this.categories = categoriesData.data.data
        let articleData = await api.getArticle(this.$route.params.aid)
        const data = articleData.data.data
        this.form.id = data.id
        this.form.title = data.title
        this.form.status = data.status
        this.form.category =data.category.id
        this.form.content = data.content
        this.form.tags = data.tags.map((tag)=>{ return tag.id })
        this.form.allowComment = data.allowComment
        this.form.cover = data.cover
        this.editor.codemirror.getDoc().setValue(this.form.content)
      })()
    },
    mounted(){
      this.editor = new Editor()
      this.editor.render();
    },
    methods: {
      submitForm(formName,status,event) {
          debugger
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.form.content = this.editor.codemirror.getValue()
            this.fullscreenLoading = true
            this.form.status = status
            let self = this
            api.modifyArticle(this.form).then((data)=> {
              self.$notify({
                title: '成功',
                type: 'success',
                message: `文章已${event.target.innerText}`
              })
              self.fullscreenLoading = false
            }).catch((msg)=>{
              self.$notify.error({
                title: '错误',
                message: msg
              })
              self.fullscreenLoading = false
            })
          } else {
            return false;
          }
        })
      },
      goBack(){
        this.$confirm('文章尚未保存，确定返回？', '提示', {
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
