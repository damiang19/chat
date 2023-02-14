import { Messages } from "./messages";

export interface IConversation {
    id: number; 
    messages: Messages[];
    conversationMembers: string[];
    isGroupConversation: boolean;
    }

export class Conversation implements IConversation {
    public id: number; 
    public messages: Messages[];
    public conversationMembers: string[];
    public isGroupConversation: boolean;
    public constructor(){};
}