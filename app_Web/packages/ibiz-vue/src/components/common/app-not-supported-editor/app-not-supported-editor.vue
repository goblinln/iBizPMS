<template>
    <div class='editor-container'>
      {{$t('app.nosupport.nosupport')}}
    </div>
</template>
<script lang = 'ts'>
import { Vue, Component, Prop, Watch } from "vue-property-decorator";
@Component({})
export default class AppNotSupportedEditor extends Vue {

}
</script>

<style lang="less">
@import "./app-not-supported-editor.less";
</style>