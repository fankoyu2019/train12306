<template>
  <div v-if="!param.date">
    请到余票查询里选择一趟列车，
    <router-link to="/ticket">跳转到余票查询</router-link>
  </div>
  <div v-else>
    <p style="font-weight: bold;">
      日期 {{ param.date }}，车次: {{ param.trainCode }}，出发站: {{ param.start }}，到达站: {{ param.end }}
    </p>
    <table>
      <tr>
        <td style="width: 25px;background: #FF9900;"></td>
        <td>:已被购买</td>
        <td style="width: 20px;"></td>
        <td style="width: 25px;background: #999999;"></td>
        <td>:未被购买</td>
      </tr>
    </table>
    <br>

    <div v-for="(seatObj, carriage) in train" :key="carriage"
         style="border: 3px solid #99CCFF;
         margin-bottom: 30px;
         padding: 5px;
         border-radius: 4px">
      <div
          style="display: block;
          width: 50px;
          height: 10px;
          position: relative;
          top: -15px;
          text-align: center;
          background: white;">

        {{ carriage }}
      </div>
      <table>
        <tr>
          <td v-for="(sell, index) in Object.values(seatObj)[0]" :key="index"
              style="text-align: center">
            {{ index + 1 }}
          </td>
        </tr>
        <tr v-for="(sellList, col) in seatObj" :key="col">
          <td v-for="(sell, index) in sellList" :key="index"
              style="text-align: center;
              border: 2px solid white;
              background: grey;
              padding: 0 4px;
              color: white;"
              :style="{background: (sell > 0 ? '#FF9900' : '#999999' )}"> {{ col }}
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script>

import {defineComponent, onMounted, ref} from 'vue';
import {useRoute} from "vue-router";
import {notification} from "ant-design-vue";
import axios from "axios";

export default defineComponent({
  name: "welcome-view",
  setup() {
    const route = useRoute();
    const param = ref({});
    param.value = route.query;
    const list = ref();
    let train = ref({});
    // 查询一列火车的所有销售信息
    const querySeat = () => {
      axios.get("/business/seat-sell/query", {
        params: {
          date: param.value.date,
          trainCode: param.value.trainCode,
        }
      }).then((response) => {
        let data = response.data;
        if (data.success) {
          list.value = data.content;
          format();
        } else {
          notification.error({description: data.message});
        }
      });
    };

    /*截取当前区间，并判断是否有票*/
    const format = () => {
      let _train = {};
      for (let i = 0; i < list.value.length; i++) {
        let item = list.value[i];
        let sellDB = item.sell;
        let sell = sellDB.substr(param.value.startIndex, param.value.endIndex - param.value.startIndex);

        if (!_train["车厢" + item.carriageIndex]) {
          _train["车厢" + item.carriageIndex] = {};
        }
        if (!_train["车厢" + item.carriageIndex][item.col]) {
          _train["车厢" + item.carriageIndex][item.col] = [];
        }
        _train["车厢" + item.carriageIndex][item.col].push(parseInt(sell));
      }
      train.value = _train;
      // console.log(train);
    };

    onMounted(() => {
      if (param.value.date) {
        querySeat();
      }
    })

    return {
      param,
      querySeat,
      list,
      train,
    }
  }
})
</script>