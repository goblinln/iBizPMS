import { applyPolyfills, defineCustomElements } from 'ibiz-mobile-components/loader';

/**
 * ibiz-mobile-components 初始化
 *
 * @export
 * @param {IonicConfig} [config]
 */
export function ibizMobileComponentsInitialize(config?: any) {
  applyPolyfills().then(() => defineCustomElements(window));
}
