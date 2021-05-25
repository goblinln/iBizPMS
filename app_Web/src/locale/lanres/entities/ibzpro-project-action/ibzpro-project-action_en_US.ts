import IBZProProjectAction_en_US_Base from './ibzpro-project-action_en_US_base';

function getLocaleResource(){
    const IBZProProjectAction_en_US_OwnData = {};
    const targetData = Object.assign(IBZProProjectAction_en_US_Base(), IBZProProjectAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;