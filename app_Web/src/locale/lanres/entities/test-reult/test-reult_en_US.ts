import TestReult_en_US_Base from './test-reult_en_US_base';

function getLocaleResource(){
    const TestReult_en_US_OwnData = {};
    const targetData = Object.assign(TestReult_en_US_Base(), TestReult_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;