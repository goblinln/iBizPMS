import TestSuite_en_US_Base from './test-suite_en_US_base';

function getLocaleResource(){
    const TestSuite_en_US_OwnData = {};
    const targetData = Object.assign(TestSuite_en_US_Base(), TestSuite_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;