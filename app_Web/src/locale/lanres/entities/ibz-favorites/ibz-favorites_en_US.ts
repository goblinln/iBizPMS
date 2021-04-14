import IbzFavorites_en_US_Base from './ibz-favorites_en_US_base';

function getLocaleResource(){
    const IbzFavorites_en_US_OwnData = {};
    const targetData = Object.assign(IbzFavorites_en_US_Base(), IbzFavorites_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;