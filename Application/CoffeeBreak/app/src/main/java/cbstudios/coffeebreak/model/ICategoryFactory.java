//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : ICategoryFactory.java
//  @ Date : 03/04/2017
//  @ Author : 
//
//




public interface ICategoryFactory {
	public ILabelCategory createLabelCategory();
	public ILabelCategory createLabelCategory(Object String);
	public ITimeCategory createMultipleDayCategory();
	public ITimeCategory createSingleDayCategory();
}
