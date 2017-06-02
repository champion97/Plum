<template>
  <div class="login-wrap">
    <div class="plum-title">Admin Login</div>
    <div class="plum-login">
      <el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="0px">
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" placeholder="username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" placeholder="password" v-model="loginForm.password"
                    @keyup.enter.native="submitForm('loginForm')"></el-input>
        </el-form-item>
        <div class="login-btn">
          <el-button type="primary" @click="submitForm('loginForm')">Login</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script>
  import api from '../../provider/api'
  import auth from '../../utils/token'
  export default {
    data: function () {
      return {
        loginForm: {
          username: '',
          password: ''
        },
        rules: {
          username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
          password: [{
            required: true, message: '请输入密码', trigger: 'blur'
          }]
        }
      }
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.login()
          } else {
            return false
          }
        });
      },
      async login(){
        const result = await api.login(this.loginForm);
        if (result.data.code) { // 非0为true
          this.$message({
            showClose: true,
            message: result.data.msg,
            type: 'error'
          });
          return
        }
        auth.setToken(result.data.data)
        this.$router.push({
          path: '/summary'
        })
      }
    }

  }
</script>
<style scoped>
  .login-wrap {
    position: relative;
    width: 100%;
    height: 100%;
  }

  .plum-title {
    position: absolute;
    top: 50%;
    width: 100%;
    margin-top: -230px;
    text-align: center;
    font-size: 30px;
    color: #fff;
  }

  .plum-login {
    position: absolute;
    left: 50%;
    top: 50%;
    width: 300px;
    height: 160px;
    margin: -150px 0 0 -190px;
    padding: 40px;
    border-radius: 5px;
    background: #fff;
  }

  .login-btn {
    text-align: center;
  }

  .login-btn button {
    width: 100%;
    height: 36px;
  }
</style>
