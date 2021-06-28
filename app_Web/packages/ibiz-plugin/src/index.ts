import { default as VueImport } from 'vue';

export default {
    install(vue: typeof VueImport) {
        vue.component('app-code-editor', () => { import('./components/app-code-editor/app-code-editor') });
    },
};