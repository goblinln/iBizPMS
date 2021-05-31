/**
 * 视图加载服务
 *
 * @export
 * @class ViewLoadingService
 * @extends {LoadingServiceBase}
 */
 export class ViewLoadingService {

    /**
     * 视图loading计数器
     *
     * @type {number}
     * @memberof ViewLoadingService
     */
    public loadingCounter: number = 0
    
    /**
     * 视图loading状态变量，true时正在loading
     *
     * @type {boolean}
     * @memberof ViewLoadingService
     */
    public isLoading:boolean = false;

    /**
     * 主视图sessionid
     *
     * @type {string[]}
     * @memberof ViewLoadingService
     */
    public srfsessionid: string = '';

    /**
     * 开启视图loading
     *
     * @memberof ViewLoadingService
     */
    public beginLoading(){
        this.loadingCounter++;
        if(!this.isLoading){
            this.isLoading = true;
        }
    }

    /**
     * 关闭视图loading
     *
     * @memberof ViewLoadingService
     */
    public endLoading(){
        this.loadingCounter--;
        if(this.isLoading && this.loadingCounter == 0){
            this.isLoading = false;
        }
    }


}