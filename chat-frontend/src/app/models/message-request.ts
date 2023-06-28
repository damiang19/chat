import { MessageFile } from "./message-file";

export interface IMessageRequest {
    content: string;
    author: string;
    conversationId: number;
    messageFile: File;
}


export class MessageRequest implements IMessageRequest{
    public content: string;
    public author: string;
    public conversationId: number;
    public messageFile: File;
    constructor(){}
}