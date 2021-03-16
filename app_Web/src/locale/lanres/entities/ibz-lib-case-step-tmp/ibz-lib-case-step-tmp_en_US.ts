import IbzLibCaseStepTmp_en_US_Base from './ibz-lib-case-step-tmp_en_US_base';

function getLocaleResource(){
    const IbzLibCaseStepTmp_en_US_OwnData = {};
    const targetData = Object.assign(IbzLibCaseStepTmp_en_US_Base(), IbzLibCaseStepTmp_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;