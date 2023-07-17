
export interface IMessageRequest {
    content: string;
    author: string;
    conversationId: number;
}


export class MessageRequest implements IMessageRequest{
    public content: string;
    public author: string;
    public conversationId: number;
    constructor(){}
}