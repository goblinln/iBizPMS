export const localList: any[] = [
    {
        type: 'zh-CN',
        name: '中文简体',
    },
];

/**
 * 多语言翻译
 *
 */
export const translate: Function = (key: string, context: any, value?: string) => {
    if (context.$te(key)) {
        return context.$t(key);
    } else {
        if (context.modelService) {
            const lanResource: any = context.modelService.getPSLang(key);
            return lanResource ? lanResource : value ? value : key;
        } else {
            return value ? value : key;
        }
    }
}

/**
 * 处理语言路径映射
 *
 */
export const handleLocaleMap: Function = (key: string) => {
    switch (key) {
        case 'zh-CN':
            return 'ZH_CN';
        case 'en-US':
            return 'EN';
        default:
            return 'ZH_CN';
    }
}