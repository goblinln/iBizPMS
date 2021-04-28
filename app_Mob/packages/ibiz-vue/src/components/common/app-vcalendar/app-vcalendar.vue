<template>
  <div>
    <v-calendar class="calendar" is-expanded :attributes="config" @dayclick="dayclick" @update:to-page="changeMonth"/>
    <div v-if="illustrations" class="illustration">
      <div v-for="(il,index) in illustrations" :key="index" class="illustration-item">
          <div :style="{'background-color': il.color}" class="illustration-color"></div>
          <div class="illustration-text">{{il.text}}</div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch } from "vue-property-decorator";
import { Util } from "ibiz-core";
@Component({})
export default class AppLaydateCalendar extends Vue {
  public config: Array<any> = [
    {
      key: "today",
      highlight: true,
      dates: new Date(),
    },
  ];

  @Prop() protected signs?: any;

  @Prop() protected illustrations?: any;

  public dotClassList: Array<any> = [];

  public dayclick(day: any) {
    let dateArray = [day.year, day.month, day.day];
    this.$emit("select", dateArray);
  }

  public changeMonth(page: any) {
    this.$emit("next", page.year, page.month);
    this.$emit("selectMonth", page.month, page.year);
  }
}
</script>

<style>
.illustration {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    height: 30px;
    background-color: white;
}
.illustration .illustration-item {
    margin-left: 10px;
    margin-right: 10px;
    width: 50px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.illustration-item .illustration-color {
    height: 10px;
    width: 10px;
}
</style>