<template>
  <a-layout-sider width="200" style="background: #fff">
    <a-menu
        v-model:selectedKeys="selectedKeys1"
        mode="inline"
        :style="{ height: '100%', borderRight: 0 }"
    >
        <a-menu-item key="/welcome"><router-link to="/welcome">
          <coffee-outlined/> &nbsp; 欢迎
        </router-link></a-menu-item>
        <a-menu-item key="/about"><router-link to="/about">
          <user-outlined/> &nbsp; 关于
        </router-link></a-menu-item>
    </a-menu>
  </a-layout-sider>

</template>


<script>
import {defineComponent, ref, watch} from 'vue';
import router from "@/router";


export default defineComponent({
  name: "the-sider-view",
  setup() {
    const selectedKeys1 = ref([]);
    watch(() => router.currentRoute.value.path, (newValue) => {
      console.log('watch', newValue);
      selectedKeys1.value = [];
      selectedKeys1.value.push(newValue);
    }, {immediate: true});
    return {
      selectedKeys1,
    };
  },
});
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
