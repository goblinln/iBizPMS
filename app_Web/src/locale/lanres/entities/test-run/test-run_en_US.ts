import TestRun_en_US_Base from './test-run_en_US_base';

function getLocaleResource(){
    const TestRun_en_US_OwnData = {};
    const targetData = Object.assign(TestRun_en_US_Base(), TestRun_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;