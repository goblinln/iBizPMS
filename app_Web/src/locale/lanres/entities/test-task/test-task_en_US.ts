import TestTask_en_US_Base from './test-task_en_US_base';

function getLocaleResource(){
    const TestTask_en_US_OwnData = {};
    const targetData = Object.assign(TestTask_en_US_Base(), TestTask_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;