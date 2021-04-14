/**
 * 单例模式装饰器
 *
 * @export
 * @param {*} [params]
 * @returns {*}
 */
export function SingletonMode(params?: any): any {
    /**
     * 装饰器处理
     */
    return function (target: any) {
        return class Singleton extends target {
            /**
             * 唯一实例
             *
             * @private
             * @static
             * @type {Singleton}
             */
            private static readonly instance: Singleton = new Singleton();
            /**
             * Creates an instance of Singleton.
             */
            constructor(...args: any[]) {
                if (Singleton.instance) {
                    return Singleton.instance;
                }
                super(...args);
            }
            /**
             * 获取唯一实例
             *
             * @static
             * @returns {Singleton}
             */
            public static getInstance(): Singleton {
                return this.instance;
            }
        };
    };
}
