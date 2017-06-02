<template>
    <div class="sidebar">
        <el-menu  class="el-menu-vertical-demo" theme="dark" unique-opened router>
            <el-menu-item index="/summary">
                <i class="el-icon-information"></i>网站概况
            </el-menu-item>
            <el-submenu index="2">
                <template slot="title"><i class="el-icon-menu"></i>文章管理</template>
                <el-menu-item index="/articles/addArticle">发布新文章</el-menu-item>
                <el-menu-item index="/articles">文章列表</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
                <template slot="title"><i class="el-icon-menu"></i>评论管理</template>
                <el-menu-item index="/comments">评论列表</el-menu-item>
            </el-submenu>
            <el-submenu index="4">
                <template slot="title"><i class="el-icon-menu"></i>标签分类管理</template>
                <el-menu-item index="/tags">标签</el-menu-item>
                <el-menu-item index="/categories">分类</el-menu-item>
            </el-submenu>
        </el-menu>
    </div>
</template>

<style scoped>
    .sidebar{
        display: block;
        position: absolute;
        width: 250px;
        left: 0;
        top: 70px;
        bottom:0;
        background: #2E363F;
    }
    .sidebar > ul {
        height:100%;
    }
</style>
