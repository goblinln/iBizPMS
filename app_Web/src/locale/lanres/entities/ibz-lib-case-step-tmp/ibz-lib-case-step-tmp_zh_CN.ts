import IbzLibCaseStepTmp_zh_CN_Base from './ibz-lib-case-step-tmp_zh_CN_base';

function getLocaleResource(){
    const IbzLibCaseStepTmp_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzLibCaseStepTmp_zh_CN_Base(), IbzLibCaseStepTmp_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;