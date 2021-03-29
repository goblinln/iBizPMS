import CaseStep_en_US_Base from './case-step_en_US_base';

function getLocaleResource(){
    const CaseStep_en_US_OwnData = {};
    const targetData = Object.assign(CaseStep_en_US_Base(), CaseStep_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;