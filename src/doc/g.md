#设计模式
 
  - 设计模式的6个基本原则
    - solid&合成/聚合复用
       - s(Single-Resposibility Principle):单一职责原则：是指一个类的功能要单一，一个类只做它该做的事情(高内聚)，不越俎代庖。
       - o(Open-Closed Principle):开放原则：对扩展开放，对修改关闭，(对现有代码进行扩展，以适应新的需求&一旦设计类完成，可以独立其工作，不要对类进任何修改)
       - l(Liskov-Substituion Principle) :里氏替换原则：任何使用基类的地方，都是用子类替换，因为子类比父类增加了一定的功能。
       - i(Interface-Segregation Principle):接口隔离原则：接口力度最小化，将功能划分到每一个不能再分的子角色，为每一个子角色创建一个接口，建立单一接口，接口的方法尽量少，小而专。
       - d(Dependency-Inversion Principle):依赖倒置原则：我们的类要依赖于抽象，而不是依赖于具体--面向接口编程。说的具体些就是声明的方法的参数类型，方法的返回类型，变量的引用类型，尽可能使用抽象类型而不是具体的类型，因为抽象类型可以被他的任何一个子类型所代替。
       - 合成/聚合复用：优先使用聚合&合成关系复用代码。