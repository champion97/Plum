<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-information"></i> 网站概况</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="summary">
      网站已稳定运行



      <el-tooltip class="item" effect="dark" content="+1s" placement="top">
        <span class="time" @click="plus()">{{time}}</span>，



      </el-tooltip>
      包含 <span>{{articles}}</span> 篇文章，收到 <span>{{comments}}</span> 条评论。



    </div>
  </div>
</template>
<script>
  export default{
    data(){
      return {
        birthday: new Date(2017, 4, 26, 0, 0, 0),
        time: '',
        comments:0,
        articles:0
      }
    },
    mounted(){
      setInterval(() => {
        let now = new Date()
        now.setMonth(now.getMonth() + 1)
        let diff = now.getTime() - this.birthday.getTime()

        let days = Math.floor(diff / (24 * 3600 * 1000))
        let leave1 = diff % (24 * 3600 * 1000)
        let hours = Math.floor(leave1 / (3600 * 1000))
        let leave2 = leave1 % (3600 * 1000)
        let minutes = Math.floor(leave2 / (60 * 1000))
        let leave3 = leave2 % (60 * 1000)
        let seconds = Math.round(leave3 / 1000)

        this.time = `${days}天${hours}小时${minutes}分钟${seconds}秒`
      }, 1000);
    },
    methods: {
      plus(){
        this.birthday.setSeconds(this.birthday.getSeconds() - 1)
      }
    }
  }
</script>
<style scoped>
  .summary {
    font-size: 20px;
    margin-top: 200px;
    text-align: center;
  }

  .summary span {
    font-size: 30px;
  }

  .summary .time {
    cursor: pointer;
    user-select: none;
  }

</style>
