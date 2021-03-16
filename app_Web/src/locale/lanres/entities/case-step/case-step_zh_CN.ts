import CaseStep_zh_CN_Base from './case-step_zh_CN_base';

function getLocaleResource(){
    const CaseStep_zh_CN_OwnData = {};
    const targetData = Object.assign(CaseStep_zh_CN_Base(), CaseStep_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;