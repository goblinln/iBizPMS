import Release_en_US_Base from './release_en_US_base';

function getLocaleResource(){
    const Release_en_US_OwnData = {};
    const targetData = Object.assign(Release_en_US_Base(), Release_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;