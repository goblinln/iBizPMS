import IBZProReleaseAction_en_US_Base from './ibzpro-release-action_en_US_base';

function getLocaleResource(){
    const IBZProReleaseAction_en_US_OwnData = {};
    const targetData = Object.assign(IBZProReleaseAction_en_US_Base(), IBZProReleaseAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;