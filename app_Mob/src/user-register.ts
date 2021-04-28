import Vue from 'vue';
import AppTreeWord from './components/app-tree-word/app-tree-word.vue';
import AppRichTextEditorPMS from './components/app-mob-rich-text-editor-pms/app-mob-rich-text-editor-pms.vue'
import AppRichTextEditor from './components/app-rich-text-pms/app-rich-text-pms.vue'
import AppMobColorPicker from './components/app-mob-color-picker/app-mob-color-picker.vue'
import AppTrendsList from './components/app-trends-list/app-trends-list.vue'
import AppPmsUploadList from './components/app-pms-upload-list/app-pms-upload-list.vue'
import APPHistoryList from './components/app-history-list/app-history-list.vue'
import appProjectTeamList from './components/app-projectteam-list/app-projectteam-list.vue'
import appCaseList from './components/app-case-list/app-case-list.vue'
import CombFormItem from './components/comb-form-item/comb-form-item.vue'
import AppSelect from './components/app-mob-select-dynamic/app-mob-select-dynamic.vue'
import appTaskTeamList from './components/app-taskteam-list/app-taskteam-list.vue'

export const UserComponent = {
    install(v: any, opt: any) {
      v.component('app-tree-word', AppTreeWord);
      v.component('app-mob-rich-text-editor-pms', AppRichTextEditorPMS);
      v.component('app-rich-text-pms', AppRichTextEditor);
      v.component('app-mob-color-picker', AppMobColorPicker);
      v.component('app-trends-list', AppTrendsList);
      v.component('app-pms-upload-list', AppPmsUploadList);
      v.component('app-history-list', APPHistoryList);
      v.component('app-projectteam-list', appProjectTeamList);
      v.component('app-case-list', appCaseList);
      v.component('comb-form-item', CombFormItem);
      v.component('app-mob-select-dynamic', AppSelect);
      v.component('app-taskteam-list', appTaskTeamList);
    }
};