import IBZCaseStep_en_US_Base from './ibzcase-step_en_US_base';

function getLocaleResource(){
    const IBZCaseStep_en_US_OwnData = {};
    const targetData = Object.assign(IBZCaseStep_en_US_Base(), IBZCaseStep_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;