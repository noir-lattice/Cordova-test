export interface ConfigResp<T> {

    /**
     * 非0即不通过
     */
    code: number;

    /**
     * 数据内容
     */
    body: T;
    
}