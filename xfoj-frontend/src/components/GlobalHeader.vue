<template>
  <a-row
    id="globalHeader"
    style="margin-bottom: 16px"
    align="center"
    :wrap="false"
  >
    <a-col flex="auto">
      <a-menu
        mode="horizontal"
        theme="dark"
        :selected-keys="selectedKeys"
        @menu-item-click="doMenuClick"
      >
        <a-menu-item
          key="0"
          :style="{ padding: 0, marginRight: '38px' }"
          disabled
        >
          <div class="top">
            <img class="top-logo" src="../assets/oj.jpg" />
            <div>小凡 OJ</div>
          </div>
        </a-menu-item>
        <a-menu-item v-for="item in visibleRoutes" :key="item.path">
          {{ item.name }}
        </a-menu-item>
      </a-menu>
    </a-col>
    <a-col flex="100px">
      <div>
        {{ store.state.user?.loginUser?.userName ?? "未登录" }}
      </div>
    </a-col>
  </a-row>
</template>

<script setup lang="ts">
//引入路由文件  做成读取这个GlobalHeader文件里的路由跳转到对应页面（动态路由）
import { routes } from "@/router/routes";
import { useRoute, useRouter } from "vue-router";
import { computed, ref } from "vue";
import { useStore } from "vuex";
import checkAccess from "@/access/checkAccess";
import ACCESS_ENUM from "@/access/accessEnum";

const store = useStore();
const router = useRouter();

// 展示在菜单的路由数组
const visibleRoutes = computed(() => {
  return routes.filter((item, index) => {
    if (item.meta?.hideInMenu) {
      return false;
    }
    // 根据权限过滤菜单
    if (
      !checkAccess(store.state.user.loginUser, item?.meta?.access as string)
    ) {
      return false;
    }
    return true;
  });
});

// 默认主页
const selectedKeys = ref(["/"]);

// 路由跳转后，更新选择的菜单项
router.afterEach((to, from, failure) => {
  selectedKeys.value = [to.path];
});

setTimeout(() => {
  store.dispatch("user/getLoginUser", {
    userName: "小凡管理员",
    userRole: ACCESS_ENUM.ADMIN,
  });
}, 3000);

const doMenuClick = (key: string) => {
  router.push({
    path: key,
  });
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#globalHeader .top {
  display: flex;
  align-content: center;
  width: 80px;
  height: 30px;
  background: linear-gradient(to right, #bbb, #fff);
  color: black;
  font-size: 8px;
  text-align: center;
}

#globalHeader .top-logo {
  padding: 6px;
  width: 34px;
  height: 20px;
}
</style>
