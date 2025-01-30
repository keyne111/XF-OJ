<template>
  <div id="basicLayout">
    <!--  布局模板-->
    <a-layout style="min-height: 100vh">
      <!-- 导航栏-->
      <a-layout-header class="header">
        <global-header />
      </a-layout-header>
      <!-- 中间-->
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <!-- 底部-->
      <a-layout-footer class="footer">
        <a href="https://github.com/keyne111" target="_blank">by 小凡</a>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<style scoped>
#basicLayout {
}

#basicLayout .header {
  //background: red;
  box-shadow: #eee 1px 1px 5px;
  margin-bottom: 6px;
}

#basicLayout .content {
  background: linear-gradient(to right, #e0e5ec, #f6f8fb);

  margin-bottom: 16px;
  padding: 20px;
}

#basicLayout .footer {
  background: #efefef;
  padding: 16px;
  position: sticky;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}
</style>

<script setup>
import GlobalHeader from "@/components/GlobalHeader.vue";
</script>
