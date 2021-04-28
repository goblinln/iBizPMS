<template>
  <div class="cover">
    <div class="loading">
      <span></span>
      <span></span>
      <span></span>
      <span></span>
    </div>
  </div>
</template>

<script lang="ts">
import {
  Vue,
  Component,
  Prop,
  Provide,
  Emit,
  Watch
} from "vue-property-decorator";

@Component({
  components: {}
})

export default class AppLoading extends Vue{

}
</script>

<style lang="less">
@import "./app-loading.less";
</style>
