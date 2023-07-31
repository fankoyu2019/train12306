<template>
  <a-layout-header class="header">
    <div class="logo">
      <router-link to="/welcome" style="color: white; font-size: 18px">
        繁空12306
      </router-link>
    </div>
    <div style="float: right; color: white;">您好：{{ member.mobile }} &nbsp;&nbsp;
      <router-link to="/login" style="color: white;" @click="logout">退出登录</router-link>
    </div>
    <a-menu
        v-model:selectedKeys="selectedKeys1"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/welcome">
        <router-link to="/welcome">
          <coffee-outlined/> &nbsp; 欢迎
        </router-link>
      </a-menu-item>
      <a-menu-item key="/passenger">
        <router-link to="/passenger">
          <user-outlined/> &nbsp; 乘车人管理
        </router-link>
      </a-menu-item>
      <a-menu-item key="/ticket">
        <router-link to="/ticket">
          <user-outlined/> &nbsp; 余票查询
        </router-link>
      </a-menu-item>
      <a-menu-item key="/my-ticket">
        <router-link to="/my-ticket">
          <idcard-outlined/> &nbsp; 我的车票
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-header>
</template>


<script>
import {defineComponent, ref, watch} from 'vue';
import store from "@/store";
import {notification} from "ant-design-vue";
import router from "@/router";


export default defineComponent({
  name: "the-header-view",
  setup() {
    let member = store.state.member;
    const selectedKeys1 = ref([]);
    watch(() => router.currentRoute.value.path, (newValue) => {
      console.log('watch', newValue);
      selectedKeys1.value = [];
      selectedKeys1.value.push(newValue);
    }, {immediate: true});
    const logout = () => {
      store.commit("setMember", {})
      notification.warning({description: '退出登录！'})
    }
    return {
      selectedKeys1,
      member,
      logout
    };
  },
});
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.logo {
  float: left;
  height: 31px;
  width: 150px;
  color: white;
  font-size: 20px;
}
</style>
