//
//  GiphyCell.swift
//  iosApp
//
//  Created by Philip K. Han on 1/10/19.
//

import UIKit

class GiphyCell: UICollectionViewCell {
    
    @IBOutlet weak var gifImageView: UIImageView!
    
    func displayContent(image: UIImage) {
        gifImageView.image = image
    }
    
}
