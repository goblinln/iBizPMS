
import Vue from "vue";
import { Notice } from 'ibiz-vue';

declare module "vue/types/vue" {
    interface Vue {
        /**
         * 提示工具
         *
         * @type {Notice}
         * @memberof Vue
         */
        $Notice: Notice;
    }
}