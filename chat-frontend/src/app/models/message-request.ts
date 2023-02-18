export interface IMessageRequest {
    content: string;
    receiver: string;
    conversationId: number;
}


export class MessageRequest implements IMessageRequest{
    public content: string;
    public receiver: string;
    public conversationId: number;
    constructor(){}
}