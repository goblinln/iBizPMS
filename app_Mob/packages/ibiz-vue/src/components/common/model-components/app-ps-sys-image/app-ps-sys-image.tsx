import { IPSSysImage } from '@ibiz/dynamic-model-api';
import Vue from 'vue';
import { Component, Prop } from 'vue-property-decorator';

@Component({})
export class AppPSSysImage extends Vue {
  /**
   *  图片模型
   *
   * @type {(IPSSysImage | null)}
   * @memberof AppPSSysImage
   */
  @Prop({ default: null }) public imageModel?: IPSSysImage | null;

  /**
   * 绘制图片
   *
   * @param {string} imagePath
   * @return {*}
   * @memberof AppPSSysImage
   */
  renderImage(imagePath: string) {
    if (!imagePath) {
      return null;
    }
    return <img class="app-ps-sys-image" src={imagePath} alt="" />;
  }

  /**
   * 绘制图标
   *
   * @param {string} cssClass
   * @return {*}
   * @memberof AppPSSysImage
   */
  renderIcon(cssClass: string) {
    if (!cssClass) {
      return null;
    }
    if (cssClass && cssClass.startsWith('fa fa-')) {
      return <i class={{ [cssClass]: true, 'app-ps-sys-image': true }}></i>;
    } else {
      return <ion-icon class="app-ps-sys-image" name={cssClass}></ion-icon>;
    }
  }

  /**
   * 绘制
   *
   * @memberof AppPSSysImage
   */
  render() {
    if (!this.imageModel) {
      return null;
    }
    return this.imageModel.imagePath
      ? this.renderImage(this.imageModel.imagePath)
      : this.renderIcon(this.imageModel.cssClass);
  }
}
