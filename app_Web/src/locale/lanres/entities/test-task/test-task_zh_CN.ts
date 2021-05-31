import TestTask_zh_CN_Base from './test-task_zh_CN_base';

function getLocaleResource(){
    const TestTask_zh_CN_OwnData = {};
    const targetData = Object.assign(TestTask_zh_CN_Base(), TestTask_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;