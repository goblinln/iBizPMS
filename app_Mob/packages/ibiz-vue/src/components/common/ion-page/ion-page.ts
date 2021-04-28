import { CreateElement, RenderContext, FunctionalComponentOptions } from 'vue';

/**
 * 导出IonPage组件
 */
export const IonPage: FunctionalComponentOptions = {
    name: 'IonPage',
    functional: true,
    props: {
        className: {
            type: Object,
            default: () => {}
        }
    },
    render(h: CreateElement, { props, children }: RenderContext) {
        return h('div', { class: { 'ion-page': true, ...props.className } }, children);
    }
};