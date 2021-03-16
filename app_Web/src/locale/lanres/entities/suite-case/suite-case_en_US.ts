import SuiteCase_en_US_Base from './suite-case_en_US_base';

function getLocaleResource(){
    const SuiteCase_en_US_OwnData = {};
    const targetData = Object.assign(SuiteCase_en_US_Base(), SuiteCase_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;