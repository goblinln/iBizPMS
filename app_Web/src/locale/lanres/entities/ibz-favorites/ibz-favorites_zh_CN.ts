import IbzFavorites_zh_CN_Base from './ibz-favorites_zh_CN_base';

function getLocaleResource(){
    const IbzFavorites_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzFavorites_zh_CN_Base(), IbzFavorites_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;