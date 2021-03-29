import { DERuleCondEngine } from './de-rule-cond-engine';

describe('实体值规则引擎测试', function () {
    const rules = [
        {
            getPSDEFVRConditions: [
                {
                    paramValue: '10',
                    condOp: 'LT',
                    paramType: 'ENTITYFIELD',
                    dEFName: 'num',
                    tryMode: false,
                    condType: 'SIMPLE',
                    name: '[常规条件] 小于(<) 数据对象属性(10)',
                },
                {
                    condOp: 'LT',
                    paramType: 'CURTIME',
                    dEFName: 'updatedate',
                    tryMode: false,
                    condType: 'SIMPLE',
                    name: '[常规条件] 小于(<) 当前时间',
                },
                {
                    regExCode: '^[1-9]d*$',
                    dEFName: 'regexNum',
                    tryMode: false,
                    condType: 'REGEX',
                    name: '[正则式](^[1-9]d*$)',
                },
            ],
            condOp: 'AND',
            keyCond: true,
            tryMode: false,
            condType: 'GROUP',
            ruleInfo: '()',
            name: '[条件组]AND',
        },
        {
            getPSDEFVRConditions: [
                {
                    includeMinValue: false,
                    includeMaxValue: false,
                    maxValue: 300.0,
                    minValue: 100.0,
                    dEFName: 'num3',
                    tryMode: false,
                    condType: 'VALUERANGE2',
                    ruleInfo: '数值必须大于[100.0]且小于[300.0]',
                    name: '[数值范围] 大于 100.0 且  小于 300.0',
                },
                {
                    includeMinValue: false,
                    includeMaxValue: false,
                    maxValue: 30.0,
                    minValue: 10.0,
                    dEFName: 'num3',
                    tryMode: false,
                    condType: 'VALUERANGE2',
                    ruleInfo: '数值必须大于[10.0]且小于[30.0]',
                    name: '[数值范围] 大于 10.0 且  小于 30.0',
                },
            ],
            condOp: 'OR',
            tryMode: false,
            condType: 'GROUP',
            ruleInfo: '不能出现 「数值必须大于[100.0]且小于[300.0] 或者数值必须大于[10.0]且小于[30.0]」',
            notMode: true,
            name: '[!][条件组]OR',
        },
        {
            includeMinValue: false,
            includeMaxValue: true,
            maxValue: 50,
            minValue: 30,
            dEFName: 'strLength',
            tryMode: false,
            condType: 'STRINGLENGTH',
            ruleInfo: '内容长度必须小于[30]且大于等于[50]',
            notMode: true,
            name: '[!][字符长度] 大于 30 且  小于等于 50',
        },
    ];
    it('值规则检测', function () {
        const ruleCond = new DERuleCondEngine(rules);
        const data = {
            num: 9,
            updatedate: '2021/1/31 4:44:04',
            regexnum: 3,
            num3: 1000,
            strlength: 38,
        };
        expect(ruleCond.test({}, data)).toBe(true);
    });
});
