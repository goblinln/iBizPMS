import { PSModelCondBase } from './ps-model-cond-base';

/**
 * 逻辑项
 *
 * @export
 * @class PSModelSingleCondBase
 * @extends {PSModelCondBase}
 */
export class PSModelSingleCondBase extends PSModelCondBase {
    /**
     * 值
     *
     * @private
     * @type {(string | null)}
     * @memberof PSModelSingleCondBase
     */
    private strValue?: string;
    /**
     * 值类型
     *
     * @private
     * @type {string}
     * @memberof PSModelSingleCondBase
     */
    private strValueType?: string;
    /**
     * 参数
     *
     * @private
     * @type {string}
     * @memberof PSModelSingleCondBase
     */
    private strParam?: string;
    /**
     * 参数类型
     *
     * @private
     * @type {string}
     * @memberof PSModelSingleCondBase
     */
    private strParamType?: string;

    /**
     * 编译条件
     *
     * @param {any[]} arr
     * @memberof PSModelSingleCondBase
     */
    parse(arr: any[]): void {
        const nCount = arr.length;
        // 是否为条件起始
        let bOpStart = true;
        let bParamStart = false;
        let bValueStart = false;
        for (let i = 0; i < nCount; i++) {
            // 设置判断条件
            if (bOpStart) {
                const strText = arr[i].toString();
                this.setCondOp(strText);
                bOpStart = false;
                bParamStart = true;
                continue;
            }
            // 设置参数标识
            if (bParamStart) {
                const strText = arr[i].toString();
                this.setParam(strText);
                bParamStart = false;
                bValueStart = true;
                continue;
            }
            // 设置值
            if (bValueStart) {
                //需要是对象
                const obj = arr[i];
                if (obj instanceof Object && !(obj instanceof Array)) {
                    // 设置值类型
                    if (obj.type != null) {
                        this.setValueType(obj.type.toString());
                    }
                    // 设置条件值
                    if (obj.value != null) {
                        this.setValue(obj.value.toString());
                    }
                } else {
                    this.setValue(obj.toString());
                }
                break;
            }
        }
    }

    getValueType(): string {
        return this.strValueType!;
    }
    setValueType(strValueType: string): void {
        this.strValueType = strValueType;
    }
    getValue(): string {
        return this.strValue!;
    }
    setValue(strValue: string): void {
        this.strValue = strValue;
    }
    getParamType(): string {
        return this.strParamType!;
    }
    setParamType(strParamType: string): void {
        this.strParamType = strParamType;
    }
    getParam(): string {
        return this.strParam!;
    }
    setParam(strParam: string): void {
        this.strParam = strParam;
    }
}
