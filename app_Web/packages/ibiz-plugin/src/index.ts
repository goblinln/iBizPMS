import { default as VueImport } from 'vue';
import { AppCodeEditor } from './components/app-code-editor/app-code-editor';

export default {
    install(vue: typeof VueImport) {
        vue.component('app-code-editor', AppCodeEditor);
    },
};

export * from './components';