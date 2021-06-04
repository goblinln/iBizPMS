import TestCaseLib_en_US_Base from './test-case-lib_en_US_base';

function getLocaleResource(){
    const TestCaseLib_en_US_OwnData = {};
    const targetData = Object.assign(TestCaseLib_en_US_Base(), TestCaseLib_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;