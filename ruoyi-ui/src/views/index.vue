<!--<template>-->
<!--  <el-row :gutter="20">-->
<!--    <el-col :span="12"><div class="grid-content bg-purple">-->
<!--      <el-card class="box-card">-->
<!--      <div id="main" style="height: 350px"></div>-->
<!--      </el-card>-->
<!--    </div></el-col>-->
<!--    <el-col :span="12"><div class="grid-content bg-purple">-->
<!--      <el-card class="box-card">-->
<!--        <div style="height: 350px"></div>-->
<!--      </el-card>-->
<!--    </div></el-col>-->
<!--  </el-row>-->
<!--</template>-->

<!--<script>-->
<!--import echarts from 'echarts'-->
<!--import {echartdemo1} from "../api/people/home"-->
<!--require('echarts/theme/macarons') // echarts theme-->
<!--export default {-->

<!--  data() {-->
<!--    return {}-->
<!--  },-->
<!--  mounted() {-->
<!--    this.$nextTick(() => {-->
<!--      this.initChart()-->
<!--    })-->
<!--  },-->
<!--  beforeDestroy() {-->
<!--    if (!this.chart) {-->
<!--      return-->
<!--    }-->
<!--    this.chart.dispose()-->
<!--    this.chart = null-->
<!--  },-->
<!--  methods: {-->
<!--    async initChart() {-->
<!--      var xdata = []-->
<!--      var ydata = []-->
<!--       echartdemo1().then(response => {-->
<!--          xdata = response.data.xdata-->
<!--          ydata = response.data.ydata-->
<!--          console.log(xdata)-->
<!--          console.log(ydata)-->
<!--         var chartDom = document.getElementById('main');-->
<!--         var myChart = echarts.init(chartDom);-->
<!--         var option;-->
<!--         option = {-->
<!--           xAxis: {-->
<!--             type: 'category',-->
<!--             boundaryGap: false,-->
<!--             data: xdata-->
<!--           },-->
<!--           yAxis: {-->
<!--             type: 'value'-->
<!--           },-->
<!--           series: [-->
<!--             {-->
<!--               data: ydata,-->
<!--               type: 'line',-->
<!--               areaStyle: {}-->
<!--             }-->
<!--           ]-->
<!--         };-->
<!--         option && myChart.setOption(option);-->
<!--      });-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<template>
  <div class="home-wrapper">
    <div class="date-row">
      <div v-for="(item, index) in cardInfoData" :key="index" class="data-col">
        <el-card shadow="always" :body-style="{padding: '0px'}">
          <div class="date-block">
            <i :class="[ item.icon ]" :style="{ background: item.color }" class="icon-box" />
            <div class="date-cont">
              <CountTo class="count" :start-val="0" :end-val="item.count" :duration="3000" />
              <p class="title">{{ item.title }}</p>
            </div>
          </div>
        </el-card>
      </div>
    </div>
    <el-row class="date-box" :gutter="20">
      <el-col :span="8">
        <el-card shadow="always" :body-style="{padding: '0px'}">
          <ChartsPie :title="text" type="pie" :data="chartsPieData" class="data-desc" />
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="always" :body-style="{padding: '0px'}">
          <div class="data-desc data-lang-box">
            <div class="data-lang-title">????????????</div>
            <div v-for="(item, index) in langsData" :key="index" class="data-lang-item">
              <label>{{ item.name }}</label>
              <el-progress :percentage="item.value" :stroke-width="16" :color="item.color" />
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="always" :body-style="{padding: '0px'}">
          <ChartsPie :title="text" type="ring" :data="chartsPieData" class="data-desc" />
        </el-card>
      </el-col>
    </el-row>
    <el-row class="date-box" :gutter="20">
      <el-col :span="12">
        <el-card shadow="always" :body-style="{padding: '0px'}">
          <ChartsBar :title="title" :data="commonChartsData" class="data-chart" />
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="always" :body-style="{padding: '0px'}">
          <ChartsLine :title="title" :data="commonChartsData" class="data-chart" />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import CountTo from 'vue-count-to'
import ChartsPie from '@/components/Charts/ChartsPie'
import ChartsBar from '@/components/Charts/ChartsBar'
import ChartsLine from '@/components/Charts/ChartsLine'
export default {
  // name: 'Home',
  components: { CountTo, ChartsPie, ChartsBar, ChartsLine },
  data() {
    return {
      text: '??????????????????',
      title: '?????????????????????????????????',
      langsData: [
        { name: '????????????????????????', value: 45, color: '#2d8cf0' },
        { name: '???????????????app', value: 32, color: '#19be6b' },
        { name: '?????????????????????', value: 4, color: '#ff9900' },
        { name: '?????????????????????', value: 13, color: '#e46cbb' },
        { name: '????????????', value: 6, color: '#9a66e4' }
      ],
      cardInfoData: [
        { title: '????????????', icon: 'vue-dsn-icon-dianji', count: 682, color: '#2d8cf0' },
        { title: '????????????', icon: 'vue-dsn-icon-xinzeng', count: 259, color: '#19be6b' },
        { title: '????????????', icon: 'vue-dsn-icon-xinfeng', count: 1262, color: '#ff9900' },
        { title: '????????????', icon: 'vue-dsn-icon-dianzan', count: 508, color: '#e46cbb' },
        { title: '????????????', icon: 'vue-dsn-icon-heart', count: 379, color: '#9a66e4' }
      ],
      chartsPieData: [
        { value: 1920, name: '????????????' },
        { value: 1169, name: '????????????' },
        { value: 986, name: '????????????' },
        { value: 621, name: '????????????' },
        { value: 3256, name: '????????????' }
      ],
      commonChartsData: {
        Mon: 782,
        Tue: 925,
        Wed: 1196,
        Thu: 812,
        Fri: 328,
        Sat: 222,
        Sun: 1080
      }
    }
  }
}
</script>

<style lang="less">
@import "../assets/less/home";
</style>

