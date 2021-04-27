export const appConfig = {
    //默认主题
    defaultTheme: 'app-theme-default',
    //默认字体
    defaultFont: 'Microsoft YaHei',
    //主题集合
    themes: [
        {
            tag: 'app-theme-default',
            title: 'light',
            color: '#f6f6f6',
        },
        {
            tag: 'app-theme-blue',
            title: 'Blue',
            color: '#6ba1d1'
        },
        {
            tag: 'app-theme-dark-blue',
            title: 'Dark Blue',
            color: '#606d80'
        },
        {
            tag: 'app-theme-studio-dark',
            title: 'Studio Dark',
            color: '#aaaaaa'
        }
    ],
    //字体集合
    fonts: [
        {
            label: 'MicrosoftYaHei',
            value: 'Microsoft YaHei',
        },
        {
            label: 'SimHei',
            value: 'SimHei',
        },
        {
            label: 'YouYuan',
            value: 'YouYuan',
        },
    ],
    // 面包屑分隔符
    breadcrumbSeparator:"/"
}