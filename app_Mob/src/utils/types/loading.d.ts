import Vue from "vue";
export declare interface Loading {
    /**
     * 显示
     *
     * @param {(any | string)} [message]
     * @memberof Loading
     */
    show(message?: any | string): void
    /**
     * 隐藏
     *
     * @memberof Loading
     */
    hidden(): void;
}

declare module "vue/types/vue" {
    interface Vue {
        /**
         * 加载动画
         *
         * @type {Loading}
         * @memberof Vue
         */
        $apploading: Loading;
    }
}