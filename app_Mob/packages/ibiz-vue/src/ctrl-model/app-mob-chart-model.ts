
export class AppMobChartModel {

    /**
     * 获取数据项集合
     *
     * @returns {any[]}
     * @memberof AppChartModel
     */
    public getDataItems(): any[] {
        return [
            {
                name: 'size',
                prop: 'size'
            },
            {
                name: 'query',
                prop: 'query'
            },
            {
                name: 'page',
                prop: 'page'
            },
            {
                name: 'sort',
                prop: 'sort'
            }
        ]
    }
}